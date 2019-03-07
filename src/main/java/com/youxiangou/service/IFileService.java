package com.youxiangou.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author ljs
 * Description TODO
 * Date 2019/2/18 21:21
 **/
public interface IFileService {

    String upload(MultipartFile file, String path);
}
