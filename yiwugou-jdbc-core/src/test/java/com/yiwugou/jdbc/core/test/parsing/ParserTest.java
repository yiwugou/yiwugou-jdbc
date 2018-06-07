package com.yiwugou.jdbc.core.test.parsing;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;

/**
 * 
 * ParserTest
 * 
 * @author zhanxiaoyong@yiwugou.com
 *
 * @since 2018年5月3日 上午11:25:24
 */
public class ParserTest {
    public static void main(String[] args) {

    }

    public void test() {
        String select = "select * from user where id=1 and create_time>20140401000000 order by id";
        String insert = "insert into user values(1)";
        String update = "update user set a=1 where b=2";
        String delete = "delete from user where a=1";

        SQLStatement selectStatement = new SQLStatementParser(select).parseStatement();
        System.out.println(selectStatement);

        SQLStatement insertStatement = new SQLStatementParser(insert).parseStatement();
        System.out.println(insertStatement);

        SQLStatement updateStatement = new SQLStatementParser(update).parseStatement();
        System.out.println(updateStatement);

        SQLStatement deleteStatement = new SQLStatementParser(delete).parseStatement();
        System.out.println(deleteStatement);
    }
}
