package edu.kit.kastel.sdq.eclipse.grading.core.artemis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kit.kastel.sdq.eclipse.grading.api.artemis.mapping.IFeedback;
import edu.kit.kastel.sdq.eclipse.grading.api.model.IAnnotation;
import edu.kit.kastel.sdq.eclipse.grading.api.model.IMistakeType;
import edu.kit.kastel.sdq.eclipse.grading.core.model.annotation.Annotation;

/**
 * Deserialize Annotation from a Feedback of
 * <li> type: MANUAL_UNREFERENCED
 * <li> text: CLIENT_DATA
 * <li> detailText: $THE_JSON_BLOB
 *
 */
public class AnnotationDeserializer {

	private static final String FEEDBACK_TEXT = "CLIENT_DATA";
	private Collection<IMistakeType> mistakeTypes;

	public AnnotationDeserializer(Collection<IMistakeType> mistakeTypes) {
		this.mistakeTypes = mistakeTypes;
	}

	private Annotation convert(Annotation annotation) throws ArtemisProcessingException {
		final List<IMistakeType> matchingMistakeTypes =
				this.mistakeTypes.stream()
					.filter(mistakeType -> mistakeType.getButtonName().equals(annotation.getMistakeTypeString()))
					.collect(Collectors.toList());
		if (matchingMistakeTypes.size() != 1) {
			throw new ArtemisProcessingException("Annotation Deserialization not possible: Found " + matchingMistakeTypes.size() + " matching mistake Types containing data instead of 1. "
					+ "(For Annotation " + annotation + ")");
		}
		annotation.setMistakeType(matchingMistakeTypes.get(0));
		return annotation;
	}

	public Collection<IAnnotation> deserialize(Collection<IFeedback> feedbacks) throws ArtemisProcessingException, JsonProcessingException {
		final List<IFeedback> matchingFeedbacks = feedbacks.stream().filter(feedback -> {
			String text = feedback.getText();
			return (text != null && text.equals(FEEDBACK_TEXT));
		}).collect(Collectors.toList());

		if (matchingFeedbacks.size() != 1) {
			throw new ArtemisProcessingException("Annotation Deserialization not possible: Found " + matchingFeedbacks.size() + " feedbacks containing data instead of 1.");
		}
		final String jsonBlob = matchingFeedbacks.get(0).getDetailText();

		Annotation[] annotationArray = new ObjectMapper()
				.readValue(jsonBlob, Annotation[].class);

		Collection<IAnnotation> deserializedAnnotations = new ArrayList<>(annotationArray.length);
		for (Annotation annotation : annotationArray) {
			deserializedAnnotations.add(IAnnotation.class.cast(this.convert(annotation)));
		}
		return deserializedAnnotations;
	}
}
