package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zhuhf
 * @Date: 2020/4/22 11:37 上午
 */

public interface ResumeService {
    List<Resume> queryResumeList() throws Exception;

    void delete(Long id);

    Optional<Resume> queryResume(Long id);

    Resume save(Resume resume1);
}
