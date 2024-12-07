package model;
import java.sql.*;
public class DB {
    Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
    public DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
	}

	/** 创建数据库连接 */
	public Connection getCon() {
		try {
			String url = "jdbc:mysql://localhost:3306/gspms?useUnicode=true;characterEncoding=utf-8";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("创建数据库连接失败！");
			con = null;
			e.printStackTrace();
		}
		return con;
	}

	public ResultSet excuteQ(String sql) {
		if (sql != null && !sql.equals("")) {
			getCon();
			if (con != null) {
				try {
					st = con.createStatement();
					rs = st.executeQuery(sql);

				} catch (SQLException e) {
					rs = null;
					System.out.print(e);
				}
			}
		}
		return rs;
	}

	public int excuteU(String sql, Object[] params) {
		int flag = 0;
		if (sql != null && !sql.equals(""))
			if (params == null)
				params = new Object[0];
		getCon();
		if (con != null) {
			try {

				ps = con.prepareStatement(sql);
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				flag = ps.executeUpdate();

			} catch (SQLException e) {
				System.out.print(e);
			}
		}
		return flag;
	}

	public int excuteU(String sql) {
		int flag = 0;
		if (sql != null && !sql.equals("")) {
			// 获取数据库连接
			getCon();
			
			// 如果连接不为空
			if (con != null) {
				try {
					// 创建 PreparedStatement，直接执行没有参数的 SQL 语句
					ps = con.prepareStatement(sql);
					
					// 执行更新操作
					flag = ps.executeUpdate();

				} catch (SQLException e) {
					System.out.println(e);
				} 
			}
		}
		return flag;
	}


	public void close() {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			System.out.println("关闭ps对象失败！");
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			System.out.println("关闭ps对象失败！");
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭con对象失败！");
			e.printStackTrace();
		}
	}
	
}