package com.tj;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsUtils {
        private static final String CONN_STR="hdfs://192.168.137.131:9000";
        private static FileSystem fs;

        static{
                try {
                        Configuration configuration=new Configuration();
                        URI uri=new URI(CONN_STR);
                        fs=FileSystem.get(uri,configuration);
                } catch (URISyntaxException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        /**
        * 创建目录
        * */
        public static boolean mkdir(String dir){
                boolean isOK= false;
                try {
                        Path path=new Path(dir);
                        isOK = fs.mkdirs(path);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return  isOK;
        }

        /**
         * 创建文件，并写入内容
         */
        public static void createFile(String dst,byte []contents){
                try {
                        Path path=new Path(dst);//目标路径
                        FSDataOutputStream outputStream=fs.create(path);//创建输出流
                        outputStream.write(contents);
                        outputStream.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         * 上传到服务器
         */
        public static void uploadToHdfs(String fromPath){
                Path srcPath=new Path(fromPath);
                Path dstPath=new Path("/output2");
                try {
                        if(!fs.exists(dstPath)){
                                fs.mkdirs(dstPath);
                        }
                        fs.copyFromLocalFile(false,srcPath,dstPath);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        /**
         *查询服务端文件列表
         */
        public static FileStatus[]  queryFileFs(String srcPath)throws Exception{
                Path dstPath = new Path(srcPath);
                FileStatus [] fileStatuses =fs.listStatus(dstPath);
                return fileStatuses;
        }

        /**
         * 文件的重命名
         */
        public static boolean rename(String oldName,String newName)throws Exception{
                Path old = new Path(oldName);
                Path new_= new Path(newName);
                boolean isOk =fs.rename(old,new_);
                return isOk;
        }

        /**
         * 读取文件的内容
         */
        public static void readFile(String filePath)throws Exception{
                Path path = new Path(filePath);
                InputStream is = null;
                is =fs.open(path);
                IOUtils.copyBytes(is,System.out,4096,false);
        }

        /**
         * 文件删除
         */
        public static boolean deleteFile(String dstPath)throws Exception{
                Path path = new Path(dstPath);
                boolean isOk =fs.deleteOnExit(path);
                return isOk;
        }
}
