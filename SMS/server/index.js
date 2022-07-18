const express = require('express');
const app = express();
const db = require('./models');

app.use(express.json());

//Routers
const courseRouter = require('./routes/manageCourses');
app.use("/manageCourses", courseRouter);


db.sequelize.sync().then(() => {
    app.listen(3001, () => {
        console.log("Running on port 3001!");
    });
});


