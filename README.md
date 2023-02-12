# questions
A little Java project for my friend who asked me to create a simple application to make studiyng for an exam easier for him.
- used language is pure Java 11.0.1 without Spring (or any other) framework
- visual elements are created using Java Swing
- project created using IntelliJ IDEA 2022.3.1 (Ultimate Edition)
- actual questions and images was not added to the repository because of possible Copyrights
  - questions have to be saved in directory 'resources' in file named 'all-questions.json'
    - schema of the file can be viewed below
  - images have to be stored in directory 'resources/images' in PNG format
    - naming of the images must be in format 'XXX.png' where XXX is the number of the question in 'all-questions.json' which the image belongs to
    - there must be an image for each question in 'all-questions.json' which has fiesd 'hasImage' = true
- answers for each question are shuffled in the program logic includig their caracter int their text
  - that means the 'a)/b)/c)' in the answers are mandatory

## all-questions.json schema
[
	{
		"allQuestionsNumber": 1,
		"questionText": "The actual text of the first question",
		"answers": [
			{
				"text": "a) The actual text of the first possible answer of question number 1",
				"correct": false,
				"answerMark": "A"
			},
			{
				"text": "b) The actual text of the second possible answer of question number 1",
				"correct": false,
				"answerMark": "B"
			},
			{
				"text": "c) The actual text of the third possible answer of question number 1",
				"correct": true,
				"answerMark": "C"
			}
		],
		"correctAnswer": "C"
    "hasImage": false
	},
  ...
]
