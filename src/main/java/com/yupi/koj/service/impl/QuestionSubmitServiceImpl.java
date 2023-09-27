package com.yupi.koj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.koj.common.ErrorCode;
import com.yupi.koj.exception.BusinessException;
import com.yupi.koj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.yupi.koj.model.entity.Question;
import com.yupi.koj.model.entity.QuestionSubmit;
import com.yupi.koj.model.entity.QuestionSubmit;
import com.yupi.koj.model.entity.User;
import com.yupi.koj.model.enums.QuestionLanguageEnum;
import com.yupi.koj.model.enums.QuestionSubmitStatueEnum;
import com.yupi.koj.service.QuestionService;
import com.yupi.koj.service.QuestionSubmitService;
import com.yupi.koj.service.QuestionSubmitService;
import com.yupi.koj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author zheng
* @description 针对表【question_submit(题目提交表)】的数据库操作Service实现
* @createDate 2023-09-27 19:59:49
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private QuestionService questionService;

    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {

        String language = questionSubmitAddRequest.getLanguage();
        QuestionLanguageEnum languageEnum = QuestionLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }

        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionSubmitAddRequest.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已题目提交
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionSubmitAddRequest.getQuestionId());
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
        questionSubmit.setLanguage(language);

        questionSubmit.setStatus(QuestionSubmitStatueEnum.WATTING.getValue());
        questionSubmit.setJudgeInfo("{}");

        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入错误");
        }
        return questionSubmit.getId();
    }

}




