package com.tj.controller;

import com.tj.HdfsUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("a")
public class CustomController {


    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String add(HttpServletRequest request, Model model, @RequestParam("upFile") MultipartFile upFile){
        int maxSize =500*1024;//500kb
        String oldFileName=upFile.getOriginalFilename();//文件的原始名字
        String ext = FilenameUtils.getExtension(oldFileName);//得到后缀
        System.out.println("文件的名字是:"+oldFileName);

        //1)判断imgFile size是否合理
        if(upFile.getSize()>maxSize){//不合理
            model.addAttribute("msg", "图片太大了,"+upFile.getSize()/1024+"k");
        }else{
            //1)新文件的名字
            String newFileName =System.currentTimeMillis()+ RandomUtils.nextInt(1000000)+oldFileName;
            //2)得到服务器的上传路径
            String path=request.getSession().getServletContext().getRealPath("/");
            System.out.println("服务器上传的根路径:"+path);
			/*destFile=服务端的文件夹的路径()+新文件的名字*/

            //判断服务器的图片上传路径是否存在，如果不存在，则创建文件加
            File root = new File(path,"upload");
            if(!root.exists()){
                root.mkdirs();
            }

            //创建上传文件路径(上传到服务器的哪里。。。。******)
            File destFile = new File(root, newFileName);

            //最终要上传
            try {
                upFile.transferTo(destFile); //destFile 上传成功的文件在tomcat的路径
                System.out.println("xx:"+destFile.getPath());


                HdfsUtils.uploadToHdfs(destFile.getPath());//上传到云盘

            } catch (Exception e) {
                model.addAttribute("msg", "上传失败:"+e.getMessage());
                e.printStackTrace();
            }
        }
        //3)上传操作
        return "ok";

    }
}
