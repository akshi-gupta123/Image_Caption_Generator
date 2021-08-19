# Image_Caption_Generator

The project is to create Image Caption Generator App. The dataset used to train model is MS-COCO dataset. The dataset contains over 82000 images, each of which has at least 5 different caption annotations. 
In this project we used different Deep learning algorithms to create the model. We also develop android application which then we integrate with our deep learning model. 
The app takes an input image either using camera or from the gallery. Then it will redirect to generator page which will generate a caption as an output which describes that image.

# What are we going to do in this project?
So in this project, we created an app which takes an input of an image either using camera or from your gallery. Then it will redirect to generator page which will generate which will generate a caption as an output which describes that image.
How this is going to be happened?
1.	Download and prepare the MS-COCO dataset
2.	Limit the size of the training dataset (optional)
3.	Preprocess the image using InceptionV3
4.	Initialize InceptionV3 and load the pre-trained Imagenet weights
5.	Caching the features extracted from InceptionV3
6.	Preprocess and tokenize the captions.
7.	Split the data into training and testing.
8.	Create a tf.dataset for training.
9.	Create an android application.
10.	Create an http server and connect your dl model with this app .

## MS-COCO Dataset - COCO is a large scale object detection, segmentation, and captioning dataset. The dataset contains over 82000 images, each of which has at least 5 different caption annotations. 

# Working of an Android part
1.	As the user opens the application they redirect to the login page where if they had already registered then they just have to enter their authentication details like Email address and password to log in and then they are redirected to the home page with the message of 'Login successfully'.
2.	While if the user is not registered yet then the user can go to the signup page and create their account by entering their details like their name, Email address, password, and confirm password to check their password is matching or not. After creating the account the user is redirected to the home page with the message of "registered successfully".
3.	The Home page is the main part of our application where the user can give the picture so that the description of that picture is displayed. 
	It contains two options either to select the picture from the gallery or 	select the picture from the camera. Users can select from either 	according to their choice. 
4.	After the user selected the picture of which they want to see the description, they are directed to another page where their selected pictures and description of the picture are displayed. The user can read the description (caption) of the image from here.
5.	In addition to that, there are three dots on home page that contains several options like: - feedback Page, Developers Page and Help. 
6.	Feedback page: - On this page, the user can give their valuable feedback to the developers. They have to enter their name email and their feedback into the feedback box and click on the button submit to submit the feedback. By clicking submit button the user was asked to open the Gmail through which they want to send their valuable feedback. After the user selects their respective Gmail account they are redirected to their Gmail where their feedback and name is already written they just need to click the sent button to send the feedback to the developer.
7.	Developer page: - It contains the name of all the group members who contributed and make an effort in this project.
8.	Help page: - It contains the information about our application that what our application does and how they can use it.

# Working of Model:
To accomplish our task to generate caption related to image, we'll use an attention-based model, which enables us to see what parts of the image the model focuses on as it generates a caption.
### Preprocessing of image:
1. Firstly, preprocesses and caches a subset of images using Inception V3, then trains an encoder-decoder model and generates captions on new images using the trained model.
2. We will use InceptionV3 (which is pretrained on Imagenet) to classify each image. We will extract features from the last convolutional layer.
First, we will convert the images into InceptionV3's expected format by: Resizing the image to 299px by 299px and then Preprocess the images using the preprocess_input method to normalize the image so that it contains pixels in the range of -1 to 1, which matches the format of the images used to train InceptionV3.
3. Create a tf.keras model where the output layer is the last convolutional layer in the InceptionV3 architecture. The shape of the output of this layer is 8x8x2048.
### Preprocessing of captions:
1. We'll tokenize the captions (for example, by splitting on spaces). This gives us a vocabulary of all of the unique words in the data.
2. Next, you'll limit the vocabulary size to the top 5,000 words (to save memory). We'll replace all other words with the token "UNK" (unknown).
3. Then create word-to-index and index-to-word mappings. Finally, pad all sequences to be the same length as the longest one .
Then split the dataset into training and testing part. 


# Model:
1. Extract the features from the lower convolutional layer of InceptionV3 giving us a vector of shape (8, 8, 2048), squash that to a shape of (64, 2048).
2. This vector is then passed through the CNN Encoder (which consists of a single Fully connected layer). The RNN (here GRU) attends over the image to predict the next word.
Training:
1. Extract the features stored in the respective .npy files and then pass those features through the encoder.
2. The encoder output, hidden state(initialized to 0) and the decoder input (which is the start token) is passed to the decoder.
3. The decoder returns the predictions and the decoder hidden state.
4. The decoder hidden state is then passed back into the model and the predictions are used to calculate the loss.
5. Use teacher forcing to decide the next input to the decoder.
6. Teacher forcing is the technique where the target word is passed as the next input to the decoder.
7. The final step is to calculate the gradients and apply it to the optimizer and backpropagate.

# Evaluation:
1. The evaluate function is similar to the training loop, except we don't use teacher forcing here. The input to the decoder at each time step is its previous predictions along with the hidden state and the encoder output.
2. Stop predicting when the model predicts the end token. And store the attention weights for every time step.

Then test the model on new images to check the performance of model.
