package com.tj;

public class test {
    public static void main(String[] args) {
        //测试添加
        try {

            //boolean isOk =HdfsUtils.mkdir("/abcc");
            //System.out.println("结果:"+isOk);

            //HdfsUtils.createFile("/abcc/myfile.txt","helloasd".getBytes());

            //测试上传
            //HdfsUtils.uploadToHdfs("c:\\notes1.txt");

           /* FileStatus[] statuss =HdfsUtils.queryFileFs("/output2");
             for(FileStatus status :statuss){
                System.out.println(status.getPath());
            }*/

             //HdfsUtils.rename("/input2/file1.txt","/input2/file2.txt");

           // HdfsUtils.readFile("/input2/file2.txt");

            HdfsUtils.deleteFile("/output2/notes1.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("运行结束!!!");
    }
}
