package com.yupi.koj.service;

import com.yupi.koj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.koj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.koj.model.entity.User;

/**
* @author zheng
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2023-09-27 19:59:49
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     *
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

}
