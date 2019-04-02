package com.msg.msg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.msg.msg.entities.TrainingSession;

@CrossOrigin("*")
@RepositoryRestResource
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {

	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_trainer_id=?1 and is_canceled = 0 order by date desc", nativeQuery = true)
	List<TrainingSession> findTrainersSessions(int fk_trainer_id);

	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_trainer_id=?1 and is_canceled = 0 and date = ?2", nativeQuery = true)
	List<TrainingSession> findTrainersSessionsByDate(int fk_trainer_id, String date); 
	
	List <TrainingSession> findByIdAndDate(int id,String date);
	
	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_client_id=?1 and is_canceled = 0 order by date desc", nativeQuery = true)
	List<TrainingSession> findUserSessions(int fk_client_id);
	
	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_client_id=?1 and is_canceled = 0 and date = ?2", nativeQuery = true)
	List<TrainingSession> findUserSessionsBydate(int fk_client_id, String date); 
	
	@Query(value = "SELECT * FROM tseam_six_3.training_session where is_canceled = 1 and fk_trainer_id = ?1", nativeQuery = true)
	List<TrainingSession> findCanceledSessions(int fk_trainer_id);
	
	@Query(value = "SELECT * FROM tseam_six_3.training_session where is_read = 0 and fk_trainer_id = ?1", nativeQuery = true)
	List<TrainingSession> findUnreadSessions(int fk_trainer_id);
	
//	@Modifying
//    @Query(value = "UPDATE tseam_six_3.training_session SET is_canceled = 1 where fk_trainer_id =:trainerId and idtraining_session =:sessionId", nativeQuery = true)
//    @Transactional
//    void changeSessionToCanceled(@Param("trainerId") int fk_trainer_id, @Param("sessionId") int idtraining_session);

	TrainingSession findById(int id);
}
 