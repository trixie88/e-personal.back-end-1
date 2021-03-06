package com.msg.msg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.msg.msg.entities.TrainingSession;
import com.msg.msg.entities.User;

@CrossOrigin("*") // because this web service is only used locally i have crossOrigin all (*) if
					// it was to be deployed this must change
@RepositoryRestResource
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {

	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_trainer_id=?1 and is_canceled = 0 order by date desc", nativeQuery = true)
	List<TrainingSession> findTrainersSessions(int fk_trainer_id);

	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_trainer_id=?1 and is_canceled = 0 and date = ?2", nativeQuery = true)
	List<TrainingSession> findTrainersSessionsByDate(int fk_trainer_id, String date);

	List<TrainingSession> findByIdAndDate(int id, String date);

	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_client_id=?1 and is_canceled = 0 order by date desc", nativeQuery = true)
	List<TrainingSession> findUserSessions(int fk_client_id);

	@Query(value = "SELECT * FROM tseam_six_3.training_session where fk_client_id=?1 and is_canceled = 0 and date = ?2", nativeQuery = true)
	List<TrainingSession> findUserSessionsBydate(int fk_client_id, String date);

	@Query(value = "SELECT * FROM tseam_six_3.training_session where is_canceled = 1 and fk_trainer_id = ?1", nativeQuery = true)
	List<TrainingSession> findCanceledSessions(int fk_trainer_id);

	@Query(value = "SELECT * FROM tseam_six_3.training_session where is_read = 0 and fk_trainer_id = ?1", nativeQuery = true)
	List<TrainingSession> findUnreadSessions(int fk_trainer_id);

	List<TrainingSession> findByTrainerAndNotificationStatusOrderByDate(User trainer, int notificationStatus);

	TrainingSession findById(int id);
}
