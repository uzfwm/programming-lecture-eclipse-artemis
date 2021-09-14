package edu.kit.kastel.sdq.eclipse.grading.api.backendstate;

public enum Transition {
	SET_COURSE_ID_AND_GET_EXERCISE_SHORT_NAMES(State.COURSE_SET),
	SET_EXERCISE_ID(State.COURSE_EXERCISE_SET),
	START_ASSESSMENT(State.COURSE_EXERCISE_SUBMISSION_SET_SUBMISSION_STARTED),
	START_CORRECTION_ROUND_1(State.COURSE_EXERCISE_SUBMISSION_SET_SUBMISSION_STARTED),
	START_CORRECTION_ROUND_2(State.COURSE_EXERCISE_SUBMISSION_SET_SUBMISSION_STARTED),
	SET_ASSESSED_SUBMISSION_BY_PROJECT_NAME(State.COURSE_EXERCISE_SUBMISSION_SET),
	LOAD_AGAIN(State.COURSE_EXERCISE_SUBMISSION_SET_SUBMISSION_STARTED),
	RELOAD_ASSESSMENT(State.COURSE_EXERCISE_SUBMISSION_SET_SUBMISSION_STARTED),
	SAVE_ASSESSMENT(State.COURSE_EXERCISE_SUBMISSION_SET_SUBMISSION_SAVED),
	SUBMIT_ASSESSMENT(State.COURSE_EXERCISE_SET),
	ON_ZERO_POINTS_FOR_ASSESSMENT(State.COURSE_EXERCISE_SET),
	ON_RESET(State.NO_STATE);

	private State to;

	Transition(State to) {
		this.to = to;
	}

	public State getTo() {
		return this.to;
	}
}