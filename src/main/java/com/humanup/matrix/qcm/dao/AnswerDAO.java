package com.humanup.matrix.qcm.dao;

import com.humanup.matrix.qcm.dao.entities.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerDAO extends CrudRepository<Answer, Long> {

  //  @Query("SELECT a FROM Answer a WHERE lower(a.emailPerson) like %:emailPerson% and a.choice.choiceId = :choiceId ")
    Answer findAnswerByEmailPersonAndChoice(String emailPerson, Long choice);
    List<Answer> findAll();
    //Answer findById(Long id);
    @Query("SELECT a FROM Answer a WHERE lower(a.emailPerson) like %:emailPerson% ")
    List<Answer> findListAnswerByEmailPerson(String emailPerson);
}
