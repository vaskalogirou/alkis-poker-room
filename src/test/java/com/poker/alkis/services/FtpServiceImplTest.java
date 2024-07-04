package com.poker.alkis.services;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.util.FileCopyUtils;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.poker.alkis.AlkisPokerRoomApplicationTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FtpServiceImplTest extends AlkisPokerRoomApplicationTests {
    
    @Autowired
    private FtpRemoteFileTemplate template;
    
    @Autowired
    private FtpService ftpService;
    
    @Test
    @Disabled
    void uploadStringToFtpServer() {
        String content = "name,phone\nvasilis,1234\njohn,456";
        String fileName = "fake_csv.csv";
        
        // this test will actually upload a file to the real ftp server
        String sent = ftpService.uploadString(content, fileName);
        
        assertEquals("fake_csv.csv", sent);
    }
    
    @Test
    @Disabled
    void downloadFileFromFtpServer() {
        // this test will actually download the file from the real ftp server if it exists
        String fileNameToCreate = "src/test/resources/csv_export_local_dir/hello_csv_downloaded.txt";
        var success = template.get("csv_export/hello_csv.txt",
                                   inputStream -> FileCopyUtils.copy(inputStream, new FileOutputStream(fileNameToCreate)));
        
        assertTrue(success);
    }
    
}
