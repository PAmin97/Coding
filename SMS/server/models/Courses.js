module.exports = (sequelize, DataTypes) => {
    const Courses = sequelize.define("Courses", {
        CRN: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        courseName: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        professor: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        days: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        startTime: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        endTime: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        capacity: {
            type: DataTypes.INTEGER,
            allowNull: false,
        }
    });

    Courses.associate = (models) => {
        Courses.hasMany(models.addCourse, {
            onDelete: "cascade",
        })
    }

    return Courses
}