var express = require('express');
var app = express();
var connection = require('./sql')
var bodyParser = require('body-parser');
// var interface = require('./interface');
app.all('*',function(req,res,next){
    res.header('Access-Control-Allow-Origin','*');//*表示可以跨域任何域名都行 也可以填域名表示只接受某个域名
    res.header('Access-Control-Allow-Headers','X-Requested-With,Content-Type');//可以支持的消息首部列表
    res.header('Access-Control-Allow-Methods','PUT,POST,GET,DELETE,OPTIONS');//可以支持的提交方式
    res.header('Content-Type','application/json;charset=utf-8');//请求头中定义的类型
    next();
});

app.use(bodyParser.urlencoded({extended:true}));//Context-Type 为application/x-www-form-urlencoded 时 返回的对象是一个键值对，当extended为false的时候，键值对中的值就为'String'或'Array'形式，为true的时候，则可为任何数据类型。
app.use(bodyParser.json());//用于解析json 会自动选择最为适宜的解析方式
// 创建一个表示当前日期和时间的Date对象
var today = new Date();
// 使用拼接字符串和补零方法获取指定格式的日期
var formattedDate = today.getFullYear() + "-" + (today.getMonth() + 1).toString().padStart(2, '0') + "-" + (today.getDate()-1).toString().padStart(2, '0');
var formattedDate1 = today.getFullYear() + "-" + (today.getMonth() + 1).toString().padStart(2, '0') + "-" + (today.getDate()).toString().padStart(2, '0');

// 打印日期字符串
console.log(formattedDate); // 2023-09-02
console.log(formattedDate1);
// 每日新增用户
app.get('/data1', (req, res) => {
    connection.query('select * from comm.average_visits',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})
// RMF模型
app.get('/data2', (req, res) => {
    connection.query('select * from comm.buonce_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})
// 用户行为
app.get('/data3', (req, res) => {
    connection.query('select * from comm.consoildate_retention_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})
// 次日留存率
app.get('/data4', (req, res) => {
    connection.query('select * from comm.fiveday_retenition_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data5', (req, res) => {
    connection.query('select * from comm.nextday_retenition_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data6', (req, res) => {
    connection.query('select * from comm.overall_statistics',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data7', (req, res) => {
    connection.query('select * from comm.repurchase_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data8', (req, res) => {
    connection.query('select * from comm.rfm_usertype',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data9', (req, res) => {
    connection.query('select * from comm.severnday_retenition_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data10', (req, res) => {
    connection.query('select * from comm.statistics_four_behaviors_of_users',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data11', (req, res) => {
    connection.query('select * from comm.the_most_repeat_purchases',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data12', (req, res) => {
    connection.query('select * from comm.the_number_of_purchases_person',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data13', (req, res) => {
    connection.query('select * from comm.threeday_retenition_rate',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data14', (req, res) => {
    connection.query('select * from comm.type_of_product_purchased_the_most_repeatedly',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data15', (req, res) => {
    connection.query('select * from comm.user_add_everyday order by first_day desc',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data16', (req, res) => {
    connection.query('select * from comm.user_behaviors_change_hour',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})


app.get('/data17', (req, res) => {
    connection.query('select * from comm.user_rank',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data18', (req, res) => {
    connection.query('select * from comm.user_recenvy_frequency',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data19', (req, res) => {
    connection.query('select * from comm.user_score',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})

app.get('/data20', (req, res) => {
    connection.query('select * from comm.users_with_most_repeat_purchases',function(error,results,fields){
        //console.log(results[0])
        res.json(results)
    })
})



app.get('/chinaData', (req, res) => {
   
    const chinaData = require('./china.json');
    res.json(chinaData) 
    })

//创建一个Express应用，并通过app.listen()方法将其监听在9898端口上。
//一旦应用成功启动并开始接受请求，控制台将输出 “数据接口已启动！” 的提示。
app.listen(9898,()=>{
    console.log("数据接口已启动！")
});

