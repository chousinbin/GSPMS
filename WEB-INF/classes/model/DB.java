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
			System.out.println("�������ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	/** �������ݿ����� */
	public Connection getCon() {
		try {
			String url = "jdbc:mysql://localhost:3306/gspms?useUnicode=true;characterEncoding=utf-8";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("�������ݿ�����ʧ�ܣ�");
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
			// ��ȡ���ݿ�����
			getCon();
			
			// ������Ӳ�Ϊ��
			if (con != null) {
				try {
					// ���� PreparedStatement��ֱ��ִ��û�в����� SQL ���
					ps = con.prepareStatement(sql);
					
					// ִ�и��²���
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
			System.out.println("�ر�ps����ʧ�ܣ�");
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			System.out.println("�ر�ps����ʧ�ܣ�");
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("�ر�con����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
}