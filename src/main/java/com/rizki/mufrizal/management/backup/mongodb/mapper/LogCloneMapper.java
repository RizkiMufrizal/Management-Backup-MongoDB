package com.rizki.mufrizal.management.backup.mongodb.mapper;

import com.rizki.mufrizal.management.backup.mongodb.domain.LogClone;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:13:19 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package com.rizki.mufrizal.management.backup.mongodb.mapper
 *
 */
public class LogCloneMapper implements RowMapper<LogClone> {

    @Override
    public LogClone mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LogClone logClone = new LogClone();
        logClone.setId(resultSet.getString("id"));
        logClone.setTanggal(resultSet.getDate("tanggal"));
        return logClone;
    }

}
