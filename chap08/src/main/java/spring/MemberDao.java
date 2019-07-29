package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {

		List<Member> results = jdbcTemplate.query(
				"select * from spring5fs.MEMBER where EMAIL = ?",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				},email);
		return results.isEmpty() ? null : results.get(0);
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from spring5fs.MEMBER", (ResultSet rs,int rowNum) -> {
			Member member = new Member(
					rs.getString("EMAIL"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime());
			member.setId(rs.getLong("ID"));
			return member;
		});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from spring5fs.MEMBER", Integer.class);
		return count;
	}

	public void update(Member member) {
		jdbcTemplate.update(
				"update spring5fs.MEMBER set NAME = ?,PASSWORD = ? where EMAIL = ?",
				member.getName(),member.getPassword(),member.getEmail());
	}

	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update((Connection con)-> {
			PreparedStatement pstmt = con.prepareStatement(
					"insert into spring5fs.MEMBER (EMAIL,PASSWORD,NAME,REGDATE) values (?,?,?,?)",
					new String[]{"ID"});
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4,
					Timestamp.valueOf(member.getRegisterDateTime()));
			return pstmt;
		},keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}



//	private static long nextId = 0;
//	private Map<String, Member> map = new HashMap<>();



}
