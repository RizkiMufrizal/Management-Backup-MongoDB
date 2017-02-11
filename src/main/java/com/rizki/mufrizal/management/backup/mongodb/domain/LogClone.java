package com.rizki.mufrizal.management.backup.mongodb.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:10:48 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package com.rizki.mufrizal.management.backup.mongodb.domain
 *
 */
public class LogClone implements Serializable {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Date tanggal;

}
