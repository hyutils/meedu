package com.chaojilaji.meedudata;

import com.chaojilaji.meedudata.file.FileUtils;
import com.chaojilaji.meedudata.file.FolderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {

    private static final String folderName = "biz";
    private static final String filePath = "content.txt";

    @RequestMapping("/index")
    public String index(HttpServletResponse response, HttpServletRequest request, Model model) {
        response.setHeader("X-Frame-Options", "DENY");
        return View.INDEX;
    }

    @RequestMapping("/backbackback/back")
    public String back(HttpServletResponse response, HttpServletRequest request, Model model) {
        response.setHeader("X-Frame-Options", "DENY");
        return View.BACK;
    }

    @PostMapping("/api/v1/content/save")
    @ResponseBody
    public Result save(@RequestParam String text,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        FileUtils.createFileWithRelativePath(folderName,filePath,text);
        return Result.success(CommonResultStatus.OK);
    }

    @GetMapping("/api/v1/content")
    @ResponseBody
    public Result getContent(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        String content = FileUtils.getFileContent2(folderName+"/"+filePath,false);
        return Result.success(content);
    }


}
