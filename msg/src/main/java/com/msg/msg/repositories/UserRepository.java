package com.msg.msg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.msg.msg.entities.Area;
import com.msg.msg.entities.TrainingType;
import com.msg.msg.entities.User;

@CrossOrigin("*") // because this web service is only used locally i have crossOrigin all (*) if
					// it was to be deployed this must change
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByAreasAndPrice(Area area, double price);

	List<User> findByPriceGreaterThanAndPriceLessThanEqual(double minPrice, double maxPrice);

	List<User> findByTrainingTypesAndPrice(TrainingType trainingType, double price);

	List<User> findByAreasAndTrainingTypesAndPrice(Area area, TrainingType trainingType, double price);

	List<User> findByAreasAndTrainingTypes(Area area, TrainingType trainingType);

	List<User> findByTrainingTypes(TrainingType trainingType);

	List<User> findByAreas(Area area);

	List<User> findDistinctUsersByAreasInAndTrainingTypesIn(List<Area> areas, List<TrainingType> trainingTypes);

	List<User> findByAreasIn(List<Area> areas);

	List<User> findByTrainingTypesIn(List<TrainingType> trainingTypes);

	User findById(int id);

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

}
