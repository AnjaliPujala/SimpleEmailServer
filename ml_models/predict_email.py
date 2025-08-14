import os
import pickle
import sys
import re

# Get the folder where this script resides
script_dir = os.path.dirname(os.path.abspath(__file__))

# Paths to vectorizer, model, and email bodies
vectorizer_path = os.path.join(script_dir, 'email_vectorizer.pkl')
model_path = os.path.join(script_dir, 'email_nn_model.pkl')
bodies_path = os.path.join(script_dir, 'email_bodies.pkl')  # List of email bodies

# Load vectorizer, model, and email bodies
with open(vectorizer_path, 'rb') as f:
    vectorizer = pickle.load(f)

with open(model_path, 'rb') as f:
    model = pickle.load(f)

with open(bodies_path, 'rb') as f:
    email_bodies = pickle.load(f)

# Get subject from command-line args
subject = " ".join(sys.argv[1:])

# Transform subject
subject_vect = vectorizer.transform([subject])

# Find nearest neighbor
distances, indices = model.kneighbors(subject_vect, n_neighbors=1)
predicted_body = email_bodies[indices[0][0]]

# Remove any names after "Hi" or "Hello" till comma
predicted_body = re.sub(r'(?i)^(hi|hello|Hello|Hi)\s+[^,]+,', r'\1,', predicted_body)

# Output
print(predicted_body)
