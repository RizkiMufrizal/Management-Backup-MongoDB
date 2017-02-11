package com.rizki.mufrizal.management.backup.mongodb.repository.impl;

import com.rizki.mufrizal.management.backup.mongodb.domain.LogClone;
import com.rizki.mufrizal.management.backup.mongodb.mapper.LogCloneMapper;
import com.rizki.mufrizal.management.backup.mongodb.repository.LogCloneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:16:51 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package com.rizki.mufrizal.management.backup.mongodb.repository.impl
 *
 */
@Repository
public class LogCloneRepositoryImpl implements LogCloneRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL = "select * from tb_log_clone";

    @Override
    public List<LogClone> getLogClones() {
        return jdbcTemplate.query(SQL, new LogCloneMapper());
    }

}
