import requests
from bs4 import BeautifulSoup
import os

player_links = {}

# Define the file path
file_path = os.path.join(os.path.dirname(__file__), 'teams', 'players.txt')

# Open the text file for reading
with open(file_path, 'r') as file:
    # Iterate through each line in the file
    for line in file:
        # Split the line by comma to separate player name and link
        parts = line.strip().split(',')
        # Extract the player name and link
        player_name = parts[0].strip()
        player_link = parts[1].strip()
        # Store the player name and link in the dictionary
        player_links[player_name] = player_link

# Print the dictionary to verify the data
print(player_links)

# URL of the webpage containing player attributes
url = 'https://www.2kratings.com/giannis-antetokounmpo'

# Fetch the HTML content of the webpage
try:
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        html_content = response.text

        # Parse the HTML content using BeautifulSoup
        soup = BeautifulSoup(html_content, 'html.parser')

        # Find all <span> elements with class containing "attribute-box"
        attribute_spans = soup.find_all('span', class_='attribute-box')

        # Indices of the desired attribute elements (0-based)
        desired_indices = [0, 7, 15, 24, 42, 43, 30, 39]

        # Extract the attribute values corresponding to the desired indices
        attribute_values = [int(attribute_spans[index].text.strip()) for index in desired_indices if index < len(attribute_spans)]

        # Calculate offensive rating
        offensive_rating = round(sum(attribute_values[:6]) / 6)

        # Calculate defensive rating
        defensive_rating = round(sum(attribute_values[6:]) / 2)

        # Extract player's name
        player_name_element = soup.find('h1', class_='header-title')
        player_name = player_name_element.text.strip() if player_name_element else "Unknown Player"

        # Write data to a text file
        with open('test.txt', 'w') as file:
            file.write(f"{player_name},{offensive_rating},{defensive_rating}\n")

        print("Data written to test.txt successfully.")

        url = 'https://www.nba.com/team/1610612749/bucks'
        response = requests.get(url, headers=headers)
        html_content = response.text
        soup = BeautifulSoup(html_content, 'html.parser')

        # Find the specific <div> containing player names
        roster_div = soup.find('div', class_='TeamRoster_content__Owdiz')

        # Find all <a> elements within the roster <div>
        player_name_elements = roster_div.find_all('a', class_='Anchor_anchor__cSc3P')

        # Extract player names
        player_names = [element.text.strip() for element in player_name_elements]

        # Print the extracted player names
        for name in player_names:
            print(name)
        
except requests.RequestException as e:
    print("Error fetching webpage:", e)