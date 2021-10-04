package com.example.mybatis_plus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.example.mybatis_plus.mapper.UserMapper;
import com.example.mybatis_plus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void generateCode() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                        "root", "")
                .globalConfig(builder -> {
                    builder.author("idivq")
                            .enableSwagger()
                            .fileOverride()
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")
                            .dateType(DateType.ONLY_DATE)
                            .commentDate("yyyy-MM-dd");
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.mybatis_plus")
                            .moduleName("mp");

                })
                .strategyConfig(builder -> {
                    builder.addInclude("user")//设置要扫描的表名, 这个地方可以传入多个，用逗号分开。你可以自己写一个生成器
                            //把你的数据库的所有表名加到一个list里面传进去
                            .entityBuilder()
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT))
                            .addTableFills(new Property("gmt_update", FieldFill.INSERT_UPDATE))
                            .enableLombok()
                            .idType(IdType.AUTO)//全局主键自增. 某个表不是这样的话你自己手动配一下。
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User(null, "测试工具人9号", 3, "1@1.com", null, null, null);
        int result = userMapper.insert(user);
        System.out.println(result);

    }

    @Test
    public void testUpdate() {
        User user = new User(1L, "测试工具人10号", 3, "1@1.com", null, null, null);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(4L);
        System.out.println(user);

//        List<User> userList = userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L));
//        userList.forEach(System.out::println);

    }

    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询
        map.put("name", "Jack");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }


    @Test
    public void testPage() {
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getPages());
    }

    @Test
    public void deleteById() {
        userMapper.deleteById(4L);
    }
}
