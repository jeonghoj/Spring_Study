package dbquery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbQuery_long {
    private DataSource dataSource;

    public DbQuery_long(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection(); // 커넥션 풀에서 가져오기
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select count(*) from spring5fs.MEMBER")) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(conn!=null)
                try {
                    conn.close(); // 커넥션 풀 반환
                } catch (SQLException e) {
                }
        }
    }

}
