package com.rizki.mufrizal.management.backup.mongodb.controller;

import com.rizki.mufrizal.management.backup.mongodb.repository.LogCloneRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since Feb 11, 2017
 * @Time 9:19:35 PM
 * @Encoding UTF-8
 * @Project Management-Backup-MongoDB
 * @Package com.rizki.mufrizal.management.backup.mongodb.controller
 *
 */
@Controller
public class LogCloneController {

    @Autowired
    private LogCloneRepository logCloneRepository;

    @Secured(value = {"ROLE_USER", "ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "IndexView";
    }

    @Secured(value = {"ROLE_USER", "ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/jadwal-backup", method = RequestMethod.GET)
    public String getLogClone(Model model) {
        model.addAttribute("logclone", logCloneRepository.getLogClones());
        return "LogCloneView";
    }

    @Secured(value = {"ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/backup", method = RequestMethod.GET)
    public String getBackup(Model model) {
        model.addAttribute("backups", getFolders("/var/backups/mongobackups", ""));
        return "BackupView";
    }

    @Secured(value = {"ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/backup-month", method = RequestMethod.GET)
    public String getBackupMonth(Model model, @RequestParam("month") String month) {
        model.addAttribute("backups", getFolders("/var/backups/mongobackups", month));
        return "BackupMonthView";
    }

    @Secured(value = {"ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/backup-month-date", method = RequestMethod.GET)
    public String getBackupDate(Model model, @RequestParam("month") String month, @RequestParam("date") String date) {
        model.addAttribute("backups", getFolders("/var/backups/mongobackups", month + "/" + date));
        return "BackupDateView";
    }

    @Secured(value = {"ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/backup-month-date-hours", method = RequestMethod.GET)
    public String getBackupHours(Model model, @RequestParam("month") String month, @RequestParam("date") String date, @RequestParam("hours") String hours) {
        model.addAttribute("backups", getFiles("/var/backups/mongobackups", month + "/" + date + "/" + hours + "/" + "th-prod-2016"));
        return "BackupHoursView";
    }

    @Secured(value = {"ROLE_ADMINISTRATOR"})
    @RequestMapping(value = "/backup-delete", method = RequestMethod.GET)
    public String deleteBackup(@RequestParam("month") String month) {
        try {
            FileUtils.deleteDirectory(new File("/var/backups/mongobackups/" + month + "/"));
        } catch (IOException ex) {
            Logger.getLogger(LogCloneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/backup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "LoginView";
    }

    private List<Map<String, Object>> getFolders(String path, String parameter) {
        File folder = new File(path + "/" + parameter);
        File[] listOfFolders = folder.listFiles();

        List<Map<String, Object>> lists = new ArrayList<>();

        for (File listOfFolder : listOfFolders) {
            if (listOfFolder.isDirectory()) {
                Map<String, Object> map = new HashMap<>();
                map.put("folder", listOfFolder.getName());
                map.put("path", path + "/" + parameter);
                lists.add(map);
            }
        }

        return lists;
    }

    private List<Map<String, Object>> getFiles(String path, String parameter) {
        File folder = new File(path + "/" + parameter);
        File[] listOfFolders = folder.listFiles();

        List<Map<String, Object>> lists = new ArrayList<>();

        for (File listOfFolder : listOfFolders) {
            if (listOfFolder.isFile()) {
                Map<String, Object> map = new HashMap<>();
                map.put("file", listOfFolder.getName());
                map.put("path", path + "/" + parameter);
                lists.add(map);
            }
        }

        return lists;
    }

}
