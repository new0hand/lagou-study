package com.lagou.edu.service.impl;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zhuhf
 * @Date: 2020/4/22 11:37 上午
 */
@Service
//@Transactional
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeDao resumeDao;

    @Override
    public List<Resume> queryResumeList() throws Exception {
        return resumeDao.findAll();
    }

    @Override
    public void delete(Long id) {
        resumeDao.deleteById(id);
    }

    @Override
    public Optional<Resume> queryResume(Long id) {
        return resumeDao.findById(id);
    }

    @Override
    public Resume save(Resume resume1) {
        return resumeDao.save(resume1);
    }
}
