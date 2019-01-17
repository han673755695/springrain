## 5.0.0 项目入口是springrain-system-web,基于Istio实现微服务,正在整理文档.

## 实现了什么?
* 不增加学习成本,像单体一样开发分布式微服务.
* 不修改业务代码可以实现单体,分层,微服务多种部署模式切换.
* 内置同步的分布式事务实现. 

## 实现思路
* 启动加载springbean时,先检查本地是否有实现,如果没有就启动RPC远程调用.整个过程对开发人员无感知.
* 基于GRPC协议调用和事务通知.
* 如果开启了分布式事务,入口方法作为事务控制器,由入口方法通过grpc协议RPC通知事务提交或者回滚.
* 基于Istio实现微服务的监控,熔断,限流.

## 体验单体到分层切换
* 修改springrain-system-web依赖springrain-system-service,作为客户端,不再依赖springrain-system-serviceimpl.
* springrain-system-serviceimpl添加springrain-grpc-server依赖,作为服务端.
* 启动springrain-system-serviceimpl
* 启动springrain-system-web
* 访问http://127.0.0.1:8080/ 

### 项目名为springrain[春雨]我的个人博客是 http://www.weicms.net QQ群是:238904840 </br>
### 文档
https://gitee.com/chunanyong/springrain/tree/master/springrain-system/springrain-system-web/doc  </br>
### 代码生成器
https://gitee.com/chunanyong/springrain/tree/master/springrain-system/springrain-system-web/gencode  </br>
### sql脚本
https://gitee.com/chunanyong/springrain/tree/master/springrain-system/springrain-system-web/sql  </br>


springrain是spring/springboot的极简封装,spring/springboot一站式开发的范例.

springrain本身就是一个完整的eclipse项目,spring/springboot一站式开发的范例,包含spring core,spring jdbc,spring mvc.可以认为就是一个spring/springboot的demo.

springrain 自带一个代码生成器,能够生成对表的增删改查的逻辑代码,以及前台页面样式和js文件

spring良好的扩展性,集成度,IOC,AOP事务,已经是项目的基础条件.

项目只依赖spring,没有hibernate,struts,ibatis.

使用shiro权限控制到按钮级

结合shiro,redis实现了天然的分布式session共享

hibernate太过复杂,运用不好就会造成很大影响,ibatis的优势就是把sql写入xml文件,利于数据库调优和sql语句管理.数据库调优可以使用druid直接输出比较慢的sql,比分析xml中的语句更直观,关于sql管理,springrain所有的sql语句都使用Finder封装,只要查看Finder在项目中的引用,就能查看项目中所有的sql语句,就此,ibatis的优势就很小了.

struts 和 spring mvc 相比,个人感觉还是有点差距的.

一些测试案例：

```java
//就极简而言,一个数据库只需要一个Service,就可以管理这个数据库的任意一张表 
//@Test  查询基本类型
public void testObject() throws Exception{
       // Finder finder=new Finder("select id from t_user where 1=1 ");
        Finder finder=Finder.getSelectFinder(User.class,"id").append(" WHERE 1=1 "); 
         finder.append("and id=:userId ").setParam("userId", "admin");
        String id = baseDemoService.queryForObject(finder, String.class);
        System.out.println(id);

}

//@Test 查询一个对象
public void testObjectUser() throws Exception{
        //Finder finder=new Finder("select * from t_user where id=:userId order by id"); 
Finder finder=Finder.getSelectFinder(User.class).append(" WHERE id=:userId order by id desc "); 
        finder.setParam("userId", "admin");
        User u = baseDemoService.queryForObject(finder, User.class);
        System.out.println(u.getName());

}
//@Test 查询分页
public void testMsSql() throws Exception{
        //Finder finder=new Finder("select * from t_user order by id");
        Finder finder=Finder.getSelectFinder(User.class).append(" order by id desc ");
        Listlist = baseDemoService.queryForList(finder, User.class, new Page(2));
        System.out.println(list.size());
        for(User s:list){
         System.out.println(s.getName());
         }
}



//@Test 调用数据库存储过程
public void testProc() throws Exception{
        Finder finder=new Finder();
        finder.setParam("unitId", 0);
        finder.setProcName("proc_up");
        Map queryObjectByProc = (Map) baseDemoService.queryObjectByProc(finder);
        System.out.println(queryObjectByProc.get("#update-count-10"));
        

}

//@Test 调用数据库函数
public void testFunction() throws Exception{
        Finder finder=new Finder();
        finder.setFunName("fun_userId");
        finder.setParam("userId", "admin");
        String userName= baseDemoService.queryForObjectByByFunction(finder,String.class);
        System.out.println(userName);
}

```