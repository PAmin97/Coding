const express = require("express");
const router = express.Router();
const { Students } = require("../models");
const bcrypt = require("bcrypt");
const { sign } = require("jsonwebtoken");
const { validateToken } = require("../middlewares/AuthMiddleware");

router.post("/", async (req, res) => {
  const { username, password } = req.body;
  bcrypt.hash(password, 10).then((hash) => {
    Students.create({
      username: username,
      password: hash,
    });
    res.json("Success");
  });
});

router.post("/login", async (req, res) => {
  const { username, password } = req.body;

  const student = await Students.findOne({ where: { username: username } });
  if (!student) return res.json({ error: "Incorrect Username" });

  bcrypt.compare(password, student.password).then((match) => {
    if (!match) return res.json({ error: "Incorrect Username or Password" });

    const accessToken = sign(
      { username: student.username, id: student.id },
      "importantsecret"
    );
    return res.json(accessToken);
  });
});

router.get("/validate", validateToken, (req, res) => {
  res.json(req.student);
});

module.exports = router;
