module.exports = (sequelize, DataTypes) => {
    const CourseInfo = sequelize.define("CourseInfo", {
        courseCRN: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        Description: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        currentCapacity: {
            type: DataTypes.INTEGER,
            allowNull: false,
        }
    });

    return CourseInfo
}