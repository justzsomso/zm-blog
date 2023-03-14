package com.zhoumin.service;

import com.zhoumin.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public ResponseResult uploadImg(MultipartFile img);
}
