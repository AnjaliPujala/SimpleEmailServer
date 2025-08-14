# ml_models/train_email_model.py
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.neighbors import NearestNeighbors
import pickle

# Load the CSV
df = pd.read_csv('C:\\Users\\HELLO\\OneDrive\\Desktop\\MyProjects\\Networking\\SimpleEmailServer\\ml_models\\emails.csv')  # Columns: sender, receiver, subject, body

# Use subject as input, body as target
subjects = df['subject'].values
bodies = df['message'].values

# Convert subjects to TF-IDF vectors
vectorizer = TfidfVectorizer()
X = vectorizer.fit_transform(subjects)

# Use NearestNeighbors to find the closest matching subject
nn = NearestNeighbors(n_neighbors=1, metric='cosine')
nn.fit(X)

# Save the model and vectorizer
with open('ml_models/email_vectorizer.pkl', 'wb') as f:
    pickle.dump(vectorizer, f)

with open('ml_models/email_nn_model.pkl', 'wb') as f:
    pickle.dump(nn, f)

# Optional: save the bodies to map later
with open('ml_models/email_bodies.pkl', 'wb') as f:
    pickle.dump(list(bodies), f)

print("Training completed and models saved!")
