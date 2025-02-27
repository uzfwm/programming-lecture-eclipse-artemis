/* Licensed under EPL-2.0 2022. */
package edu.kit.kastel.eclipse.student.view.controllers;

import java.util.List;
import java.util.Set;

import edu.kit.kastel.eclipse.common.api.ArtemisClientException;
import edu.kit.kastel.eclipse.common.api.artemis.mapping.Feedback;
import edu.kit.kastel.eclipse.common.api.artemis.mapping.IExam;
import edu.kit.kastel.eclipse.common.api.artemis.mapping.IExercise;
import edu.kit.kastel.eclipse.common.api.artemis.mapping.IStudentExam;
import edu.kit.kastel.eclipse.common.api.artemis.mapping.ResultsDTO;
import edu.kit.kastel.eclipse.common.api.client.websocket.WebsocketCallback;
import edu.kit.kastel.eclipse.common.api.controller.IStudentSystemwideController;
import edu.kit.kastel.eclipse.common.api.model.IAnnotation;
import edu.kit.kastel.eclipse.common.api.util.Pair;
import edu.kit.kastel.eclipse.common.view.controllers.AbstractArtemisViewController;
import edu.kit.kastel.eclipse.student.view.activator.Activator;

public class StudentViewController extends AbstractArtemisViewController<IStudentSystemwideController> {

	public StudentViewController() {
		super(Activator.getDefault().createSystemWideController());
		this.initializeControllersAndObserver();
	}

	public void startExercise() {
		this.systemwideController.loadExerciseForStudent();
	}

	public void onSubmitSolution() {
		this.systemwideController.submitSolution();
	}

	public void cleanWorkspace() {
		this.systemwideController.cleanWorkspace();
	}

	public Pair<ResultsDTO, List<Feedback>> getFeedbackExcerise() {
		return this.systemwideController.getFeedbackExcerise();
	}

	public boolean canSubmit() {
		return !this.systemwideController.isSelectedExerciseExpired();
	}

	public boolean canClean() {
		return this.systemwideController.isSelectedExerciseInWorkspace();
	}

	public boolean connectToWebsocket(WebsocketCallback callBack) {
		return this.systemwideController.connectToWebsocket(callBack);
	}

	public boolean canFetchFeedback() {
		return true;
	}

	public IExercise getCurrentSelectedExercise() {
		return this.systemwideController.getCurrentSelectedExercise();
	}

	@Override
	public void setExerciseId(final String exerciseShortName) {
		try {
			this.systemwideController.setExerciseIdWithSelectedExam(exerciseShortName);
		} catch (ArtemisClientException e) {
			this.viewObserver.error(e.getMessage(), e);
		}
	}

	public void setExamToNull() {
		this.systemwideController.setExamToNull();
	}

	@Override
	public List<String> getExercisesShortNamesForExam(String examShortName) {
		return this.systemwideController.getExerciseShortNamesFromExam(examShortName).stream().map(IExercise::getShortName).toList();
	}

	public IExam setExam(String examName) {
		return this.systemwideController.setExam(examName);
	}

	public IStudentExam getCurrentlySelectedExam() {
		return this.systemwideController.getExam();
	}

	public IStudentExam startExam() {
		return this.systemwideController.startExam();
	}

	public String getExamUrlForCurrentExam() {
		return this.systemwideController.getExamUrlForCurrentExam();
	}

	public void resetSelectedExercise() {
		this.systemwideController.resetSelectedExercise();
	}

	public boolean canResetExercise() {
		return this.systemwideController.isSelectedExerciseInWorkspace();
	}

	public void resetBackendState() {
		this.systemwideController.resetBackendState();
	}

	public Set<IAnnotation> getAnnotations() {
		return this.systemwideController.getAnnotations();
	}

	public String getCurrentProjectNameInEclipse() {
		return this.systemwideController.getCurrentProjectName();
	}
}
