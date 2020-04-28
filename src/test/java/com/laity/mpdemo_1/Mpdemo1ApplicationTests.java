package com.laity.mpdemo_1;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laity.mpdemo_1.entity.Book;
import com.laity.mpdemo_1.entity.User;
import com.laity.mpdemo_1.mapper.BookMapper;
import com.laity.mpdemo_1.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class Mpdemo1ApplicationTests {


	@Autowired
	private UserMapper userMapper;  //报错是因为usermapper没有在spring  需要在将其交给spring容器管理


	@Autowired
	private BookMapper bookMapper;

	//查询user表中的所有信息
	@Test
	void findAll() {
		List<User> userList = userMapper.selectList(null);
		System.out.println(userList);
	  	}

	/**
	 * 多个id批量查询
	 */
	@Test
	public void testSelectid(){
		//根据id查询数据     Batch批量
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
		System.out.println(users);

	}

	/**
	 * 简单条件查询
	 */
	@Test
	public void testSelectWhere(){
		HashMap<String,Object> map=new HashMap<>();
		map.put("name","方云");
		map.put("age","41");
		List<User> users = userMapper.selectByMap(map);
		System.out.println(users);

	}


	@Test
	public void testBook(){


	}

	/**
	 * 分页查询
	 */
	@Test
	public void testSelectPage (){
		//1.编写分页代码 可以直接new page对象，
		// 传入两个参数当前页面和每页显示条数
		Page<User> page =new Page<>(3,2);
		//调用mp分页查询过程中，底层封装
		//把分页的所有数据封装到page对象中
		IPage<User> userIPage = userMapper.selectPage(page,null);  //wrapper 传入分页条件
       //通过page对象获取分页数据
		System.out.println(page.getCurrent()); //获取分页的当前页
		System.out.println(page.getRecords()); //获取每页数据的list集合
		System.out.println(page.getSize()); //获取每页数据的list集合
		System.out.println(page.getTotal()); //获取总记录数
		System.out.println(page.getPages()); //获取总页数
		System.out.println(page.getSize()); //获取每页数据的list集合

		System.out.println(page.hasNext()); //是否有下一页
		System.out.println(page.hasPrevious()); //是否有上一页  true/false
	}


	String a="主键策略" +
			"自动增长 AUTO INCREMENT    缺点：分库分表难实现，容易造成主键冲突" +
			"每次生成随机唯一的值 UUID   缺点： 无法进行排序" +
			"redis生成id  " +
			"";

	@Test
	public void addUser(){
		User user=new User();
		//user.setId(4);
		user.setName("李淳罡");
		user.setAge(443);
		user.setEmail("315156464313213");
		int insert = userMapper.insert(user);
		System.out.println("insert:"+insert);
	}

	//自动填充  不用set到对象里面值，使用mp方式实现数据添加
	@Test
	public void addUserT(){
	for (int i=0;i<10; i++){
		User user=new User();
		//user.setId(4);
		user.setName("龙象"+i);
		user.setAge(21+i);
		user.setEmail("1000"+i+"@qq.com");
		/*user.setCreateTime(new Date());
		user.setUpdateTime(new Date());*/
		int insert = userMapper.insert(user);
		//System.out.println("insert:"+insert);
	}
	}


	/**
	 * mp实现复杂查询操作
	 * in、notIn、inSql、notinSql、exists、notExists
	 */
	@Test
	public void SelectUserT(){
		QueryWrapper<User> wrapper=new QueryWrapper<>();
		//通过QueryWrapper设置条件
		//ge 大于等于、gt 大于、le 小于等于 、lt 小于
		//第一个参数是字段名称,第二个参数设置值
		//wrapper.ge("age",30);  //age>=30

		//eq 等于、ne不等于
		wrapper.eq("name","龙象4");

		//between 范围

		//like 模糊查询
		List<User> users=userMapper.selectList(wrapper);
		System.out.println(users);

		//orderByDesc

		//last

		//指定要查询的

	}


	/**
	 * 添加

	 Shift+Enter

	 复制

	 Ctrl+D

	 移动

	 Ctrl+Shift+↑/↓  或者  Alt+Shift+↑/↓

	 删除

	 Ctrl+Y
	 */













	//修改操作
	@Test
	public void upUser(){
		User user=new User();
		user.setId("2");
		user.setName("王富贵s");

		int row =userMapper.updateById(user);
		System.out.println(row);
	}




	//删除操作 根据id
	@Test
	public void delteUser(){
		int i = userMapper.deleteById("2");
		System.out.println(i);
	}

	//批量删除操作 根据id
	@Test
	public void delteUsers(){
		int i = userMapper.deleteBatchIds(Arrays.asList("0","1"));
		System.out.println(i);
	}


	//根据条件删除
	@Test
	public void delteUsersWhere(){
		HashMap<String,Object> map=new HashMap<>();
		map.put("name","方云");
		map.put("age","41");
		int users = userMapper.deleteByMap(map);
		System.out.println(users);
	}

	/**
	 * 物理删除和逻辑删除
	 * 逻辑删除，无法查询，但是数据存在 例如回收站
	 */
	//批量删除操作 根据id
	@Test
	public void delteTese(){
		int i = userMapper.deleteById("1251710819180236801");
		System.out.println(i);
	}
//测试添加swagger


	/**
	 * 测试乐观锁
	 * 先查询再修改
	 */
	@Test
	public void testOptimistic(){

		//根据id查询
		User user= userMapper.selectById("1251537487252164609");
		//再修改
		user.setAge(200);
		userMapper.updateById(user);

	}
























}
