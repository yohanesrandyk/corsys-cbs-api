package id.co.corsys.cbs.services;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseService {
	// LIVE SYSTEM DB
	private JdbcTemplate liveJdbcTemplate;

	@Autowired
	@Qualifier("live")
	private DataSource liveDS;

	@Autowired
	private void setLiveDS() {
		this.liveJdbcTemplate = new JdbcTemplate(liveDS);
	}

	// CETAK DB
	private JdbcTemplate cetakJdbcTemplate;

	@Autowired
	@Qualifier("live")
	private DataSource cetakDS;

	@Autowired
	private void setCetakDS() {
		this.cetakJdbcTemplate = new JdbcTemplate(cetakDS);
	}

	public JdbcTemplate getJdbcTemplate(String DB) throws Exception {
		switch (DB) {
		case "001":
			return liveJdbcTemplate;
		case "002":
			return cetakJdbcTemplate;
		}
		throw new Exception("DATABASE NOT RECOGNIZED");
	}
}
