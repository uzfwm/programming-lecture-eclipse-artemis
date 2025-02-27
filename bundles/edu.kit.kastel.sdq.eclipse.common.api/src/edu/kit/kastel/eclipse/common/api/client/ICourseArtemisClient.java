/* Licensed under EPL-2.0 2022. */
package edu.kit.kastel.eclipse.common.api.client;

import java.util.List;

import edu.kit.kastel.eclipse.common.api.ArtemisClientException;
import edu.kit.kastel.eclipse.common.api.artemis.mapping.ICourse;

/**
 * REST-Client to execute calls concerning courses.
 */
public interface ICourseArtemisClient {
	/**
	 * Returns all courses for current user.
	 *
	 * @return all available courses, containing exercises and exams.
	 * @throws ArtemisClientException if some errors occur while parsing the result.
	 */
	List<ICourse> getCoursesForStudent() throws ArtemisClientException;

	/**
	 * Returns all courses for current user. Needs extra rights to be called. If
	 * user is student please use {@link #getCoursesForStudent()}.
	 *
	 * @return all available courses, containing exercises and available submissions
	 * @throws ArtemisClientException if some errors occur while parsing the result.
	 */
	List<ICourse> getCoursesForAssessment() throws ArtemisClientException;
}
