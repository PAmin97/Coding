const express = require('express');
const app = express();
const db = require('./models');
const cors = require('cors');

app.use(express.json());
app.use(cors());

//Routers
const courseRouter = require('./routes/manageCourses');
app.use("/manageCourses", courseRouter);


db.sequelize.sync().then(() => {
    app.listen(3001, () => {
        console.log("Running on port 3001!");
    });
});


