# Java ML Model

No Python is used here.

The runnable baseline in `src/main/java/.../ml/MlPredictionService.java` is a Java-native logistic scoring pipeline. It
exposes modelVersion and validates probabilities.

For a research-trained artifact, place a serialized Java model and its training metadata here. Document:

- public dataset source/license
- number of samples
- class distribution
- preprocessing
- TF-IDF/vectorization choices
- train/validation/test split
- precision/recall/F1/ROC-AUC
- model version
- limitations

Do not train on private Gmail messages.
