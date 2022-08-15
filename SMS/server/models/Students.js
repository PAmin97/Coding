module.exports = (sequelize, DataTypes) => {
    const Students = sequelize.define("Students", {
        username: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    });

    Students.associate = (models) => {
        Students.hasMany(models.addCourse, {
            onDelete: "cascade",
        })
    }

    return Students;
}