# 停车场信息管理系统

#### 介绍
停车场信息管理系统
这是第一次实训的项目，实现了停车信息的增删查改，打印账单等功能
Park-System目录下是放的代码。代码中含有erro-XXXX.txt是由于当时电脑内存不足，导致Java虚拟机报错产生的。
.docx文件是项目的使用说明书。
P.S.:有些许的不足之处，有一点点小bug

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)




1.配置要求
本系统配置要求为：运行的电脑版本为Windows 10，程序运行环境JDK 14，SQL Server数据库,程序运行平台IDEA。

2.数据库配置
创建数据库park,以及其中的基本表parkUser， parkAdmin，parkSource，parkPrice，parkBill，parkCar，parkPark。创建步骤和代码如下：
代码如下
（1）创建数据库park
create database park
（2）创建用户表
use park
create table parkUser(
	userName varchar(20) primary key,
	userPassword varchar(20),
	userRight varchar(10)
)
（3）创建管理员信息表
create table parkAdmin(
	userName varchar(20) primary key,
	adminName varchar(10),
	adminID varchar(20),
	adminBirthday varchar(20),
	adminSex varchar(5),
	adminAddress varchar(20),
	adminTel varchar(25),
	adminPhoto varchar(300)
)
（4）创建停车位基本信息
create table parkSource(
	sourceNo varchar(20)primary key,
	sourcePosition varchar(20),
	sourceIsUsed varchar(20),
	sourceGroup varchar(20)
)
（5）创建收费标准表
create table parkPrice(
	carType  primary key,
	parkPrice float,
	carComments varchar(30)
)
（6）创建车辆基本信息表
create table parkCar(
	carNo varchar(20) primary key,
	carType varchar(20),
	carOwner varchar(20),
	carTel varchar(25)
)
（7）创建停车信息表
create table parkPark(
	carNo varchar(20),
	sourceNo varchar(20),
	carStartDate datetime
	primary key(carNo,sourceNo)
)
（8）创建账单表
create table parkBill(
	billNo varchar(300) primary key,
	carNo varchar(20) ,
	carType varchar(20),
	carOwner varchar(20),
	carTel varchar(25),
	sourceNo varchar(20),
	carStartDate datetime,
	carEndDate datetime,
	carStopHours float,
	carPrice float,
	carFee float,
	billAdminUserName varchar(20),
	billAdminUserTel varchar(25)
)
创建成功后在数据库中查看如图2.1所示。

图2.1数据库配置成功图
3.操作说明
3.1运行说明
首先要在IDEA中引入改项目工程。引入成功后如图3.1所示。

图3.1引入成功ParkSystem图
在src下的dao包中找到DBUtil.java,根据自己的实际需要更改其中的静态变量url,user和password。DBUtil.java如图3.2所示。

图3.2 DBUtil.java部分截图
在view中找到DengLuJFrame.java的主函数main，运行即可到登录界面。
3.2登录
用户可以通过帐号密码进行登陆。输入正确的密码和用户名后点击登录按钮会跳转到主界面。如果用户名或密码错误则会弹出提示信息。如果是首次使用该系统的用户则要进行帐号注册。注册成功后返回登录界面进行登录。用户登录界面如图3.3所示。

图3.3 用户登录界
3.3注册
用户注册可以选择注册普通用户和管理员用户，注册时两次密码必须一致。注册管理员时，会跳转到管理员信息添加界面，只有管理员信息添加完整才能跳转到能录界面。用户注册界面如图3.4所示。

图3.4 用户注册界面
用户注册界面涉及到的管理员信息添加界面。默认的管理员照片时白色的，点击相应的按钮能实现相应的功能。点击相应的文本框能够编辑信息。管理员信息添加界面如图3.5所示。

图3.5 管理员信息添加页面
3.4主界面
主界面中含有一些按钮，点击不同的按钮跳转到不同的界面。但对于普通用户来说点击某些按钮会出现“权限不足”的提示。点击Home按钮会跳转到主界面。点击PersonalInfo进入用户信息修改界面，点击SourceInfo进入价格标准修改和账单查询界面。点击StopCar会进入停车添加界面。点击Find/Cancel进行查找车辆和取消停车。点击Infomation查看软件的信息。点击Exit退出停车场信息管理系统。如图3.6所示

图3.6 主界面
主界面中含有停车位使用情况和展示收费标准，实现展示停车位的使用情况和收费情况，让用户能够很快获取到关注的信息。
3.5用户信息管理界面
3.5.1普通用户对用户信息的管理
普通用户的账户管理只提供管理自身账户密码的功能。点击Clear按钮能实现清空密码框的内容。点击Submit按钮能实现提交修改密码的请求。普通用户信息管理界面如图3.7所示。

图3.7 普通用户页面管理
3.5.2管理员用户对用户信息的管理
管理员用户管理提供对管理员的信息和普通用户的信息管理。通过点击相应按钮来实现相应的登录用户的信息的增删改查。User Info面板显示所有登录用户的信息。Info面板显示User Info面板中被选中的信息。
管理员对普通用户的账户管理页面如图3.8所示。

图3.8 管理员对普通用户的账户管理页面
3.5.3管理员对管理员信息管理
该界面可以实现对Admin Info面板中选中的用户进行相应的操作如增、删、改、查。该界面还能实现对用户照片的修改。管理员对管理员信息管理页面如图3.9所示。

图3.9 管理员对管理员信息管理界面
3.6账单展示和修改收费标准界面
账单展示模块界面，实现对账单的分类统计，比如按月或天天统计、按车辆类型统计、按停车开始时段统计、按取消停车的时段统计。管理员和用户可以从这些统计中得到一些非常有用的信息比如停车场的收入状况、停车的高峰期等。修改停车收费标准模块实现管理员对停车收费标准的修改。管理员账单展示和修改收费标准界面如图3.10所示。

图3.10 管理员账单展示和修改收费标准界面
点击CountByMonth按钮显示账单按月分类显示，显示月份，车辆的个数，以及停车场在该月的收入。点击CountByDay按钮显示账单按天分类显示，显示日期，车辆的个数，以及停车场在该日的收入。点击CountBycType按钮显示账单按车辆类型分类显示，显示车辆类型，和该类型车辆的个数。点击CountBySTime按钮显示账单按车辆进入时间分类显示，显示车辆进入时间，该时间车辆的个数。点击CountByETime按钮显示账单按车辆出停车场时间分类显示，显示车辆驶出时间，该时间车辆的个数。
修改价格时首先选择一行并且在文本框中输入想要修改的价格，点击修改按钮，即可修改成功。
3.7停车业务模块相关界面
3.7.1添加停车界面
该界面可以实现对车辆的添加。车辆添加时必须遵循相应的规则，相应的车辆类型必须停到相应的停车位。添加车辆时，相关信息必须填写完整。添加停车界面如图3.11所示。

图3.11 添加停车界面
3.7.2取消停车界面
取消停车界面实现取消停车的功能，在取消停车前，可以根据需要，添加车辆类型、车主等信息进行查询车辆及其相关信息。办理取消停车业务时，需要先点击选中一行车辆停车信息，Car Info界面会显示车辆相关信息，点击Cancel取消停车。取消停车时需要缴费，必须缴费成功后才能真正取消停车。缴费成功后会弹出提示框，可以选择是否打印账单。取消停车界面如图3.12所示。缴费界面如图3.13所示。


图3.12 取消停车界面

图3.13 缴费界面
3.8软件相关信息界面
软件相关信息界面如图3.14所示。

图3.14 软件相关信息界面

4.代码包及其说明
代码的压缩包下：


解压后找到src文件夹。src文件夹如图4.1所示。其中Control中是对应界面的监听事件的的处理器的类。dao包中是数据库连接类与Control中的事件处理器的类相对应。entity中是应数据库中的表中的实或者关系的类。Test中是测试类，无实际用处，可以删除。view中含有Panel包（Panel包包含了几乎所有功能的面板）和一些类。view中的DengLuJFrame.java中的main函数是停车场信息管理系统的程序入口。

图4.1 src文件夹
