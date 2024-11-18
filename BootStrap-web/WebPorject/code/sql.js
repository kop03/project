const mysql = require('mysql'); //通过require(‘mysql’)语句引入了mysql模块。

//通过mysql.createConnection()方法创建了一个连接对象。在这里，指定了数据库的连接配置信息，
//包括主机（host）、用户名（user）、密码（password）、数据库名（database）和端口号（port）。
var connection = mysql.createConnection({
    host        : '192.168.88.10',
    user        : 'root',
    password    : '1234',
    database    : 'comm',
    port        : 3306
});
//如果连接成功  可以查询到数据表的信息

connection.connect((err) =>{
     if (err) return console.log("数据库连接失败")
    
     console.log("数据库连接成功！")
     //console.log(err)

});
//使用module.exports将数据库连接对象connection导出为一个模块。
module.exports = connection