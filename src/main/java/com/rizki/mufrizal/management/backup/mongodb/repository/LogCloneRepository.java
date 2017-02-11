package com.rizki.mufrizal.management.backup.mongodb.repository;

import com.rizki.mufrizal.management.backup.mongodb.domain.LogClone;
import java.util.List;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:16:15 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package com.rizki.mufrizal.management.backup.mongodb.repository
 *
 */
public interface LogCloneRepository {

    public List<LogClone> getLogClones();
}
