import requests
import cloudscraper
from bs4 import BeautifulSoup
import os
import time
import random
import unidecode

player_links = {}

# Define the file path
file_path = os.path.join(os.path.dirname(__file__), 'teams', 'players.txt')

# Open the text file for reading
with open(file_path, 'r', encoding='utf-8') as file:
    # Iterate through each line in the file
    for line in file:
        # Split the line by comma to separate player name and link
        parts = line.strip().split(',')
        # Extract the player name and link
        player_name = parts[0].strip()
        player_link = parts[1].strip()
        # Store the player name and link in the dictionary
        player_links[player_name] = player_link

url_file_mapping = {
    "https://www.nba.com/teams/sixers": "76ers.txt",
    "https://www.nba.com/teams/bucks": "bucks.txt",
    "https://www.nba.com/teams/bulls": "bulls.txt",
    "https://www.nba.com/teams/cavaliers": "cavs.txt",
    "https://www.nba.com/teams/celtics": "celtics.txt",
    "https://www.nba.com/teams/clippers": "clippers.txt",
    "https://www.nba.com/teams/grizzlies": "grizzlies.txt",
    "https://www.nba.com/teams/hawks": "hawks.txt",
    "https://www.nba.com/teams/heat": "heat.txt",
    "https://www.nba.com/teams/hornets": "hornets.txt",
    "https://www.nba.com/teams/jazz": "jazz.txt",
    "https://www.nba.com/teams/kings": "kings.txt",
    "https://www.nba.com/teams/knicks": "knicks.txt",
    "https://www.nba.com/teams/lakers": "lakers.txt",
    "https://www.nba.com/teams/magic": "magic.txt",
    "https://www.nba.com/teams/mavericks": "mavs.txt",
    "https://www.nba.com/teams/nets": "nets.txt",
    "https://www.nba.com/teams/nuggets": "nuggets.txt",
    "https://www.nba.com/teams/pacers": "pacers.txt",
    "https://www.nba.com/teams/pelicans": "pelicans.txt",
    "https://www.nba.com/teams/pistons": "pistons.txt",
    "https://www.nba.com/teams/raptors": "raptors.txt",
    "https://www.nba.com/teams/rockets": "rockets.txt",
    "https://www.nba.com/teams/spurs": "spurs.txt",
    "https://www.nba.com/teams/suns": "suns.txt",
    "https://www.nba.com/teams/thunder": "thunder.txt",
    "https://www.nba.com/teams/timberwolves": "timberwolves.txt",
    "https://www.nba.com/teams/blazers": "trailblazers.txt",
    "https://www.nba.com/teams/warriors": "warriors.txt",
    "https://www.nba.com/teams/wizards": "wizards.txt",
}

team_descriptions = {
    "https://www.nba.com/teams/sixers": "Eastern\nAtlantic",
    "https://www.nba.com/teams/bucks": "Eastern\nCentral",
    "https://www.nba.com/teams/bulls": "Eastern\nCentral",
    "https://www.nba.com/teams/cavaliers": "Eastern\nCentral",
    "https://www.nba.com/teams/celtics": "Eastern\nAtlantic",
    "https://www.nba.com/teams/clippers": "Western\nPacific",
    "https://www.nba.com/teams/grizzlies": "Western\nSouthwest",
    "https://www.nba.com/teams/hawks": "Eastern\nSoutheast",
    "https://www.nba.com/teams/heat": "Eastern\nSoutheast",
    "https://www.nba.com/teams/hornets": "Eastern\nSoutheast",
    "https://www.nba.com/teams/jazz": "Western\nNorthwest",
    "https://www.nba.com/teams/kings": "Western\nPacific",
    "https://www.nba.com/teams/knicks": "Eastern\nAtlantic",
    "https://www.nba.com/teams/lakers": "Western\nPacific",
    "https://www.nba.com/teams/magic": "Eastern\nSoutheast",
    "https://www.nba.com/teams/mavericks": "Western\nSouthwest",
    "https://www.nba.com/teams/nets": "Eastern\nAtlantic",
    "https://www.nba.com/teams/nuggets": "Western\nNorthwest",
    "https://www.nba.com/teams/pacers": "Eastern\nCentral",
    "https://www.nba.com/teams/pelicans": "Western\nSouthwest",
    "https://www.nba.com/teams/pistons": "Eastern\nCentral",
    "https://www.nba.com/teams/raptors": "Eastern\nAtlantic",
    "https://www.nba.com/teams/rockets": "Western\nSouthwest",
    "https://www.nba.com/teams/spurs": "Western\nSouthwest",
    "https://www.nba.com/teams/suns": "Western\nPacific",
    "https://www.nba.com/teams/thunder": "Western\nNorthwest",
    "https://www.nba.com/teams/timberwolves": "Western\nNorthwest",
    "https://www.nba.com/teams/blazers": "Western\nNorthwest",
    "https://www.nba.com/teams/warriors": "Western\nPacific",
    "https://www.nba.com/teams/wizards": "Eastern\nSoutheast",
}


def get_team_data(url, file_name):
    try:
        time.sleep(1)
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
            'Accept-Encoding': 'gzip, deflate, br',
            'Accept-Language': 'en-US,en;q=0.9',
        }
        response = requests.get(url, headers=headers)
        html_content = response.text
        soup = BeautifulSoup(html_content, 'html.parser')

        # Find the specific <div> containing player names
        roster_div = soup.find('div', class_='TeamRoster_content__Owdiz')

        # Find all <a> elements within the roster <div>
        player_name_elements = roster_div.find_all('a', class_='Anchor_anchor__cSc3P')

        # Extract player names
        player_names = [unidecode.unidecode(element.text.strip()) for element in player_name_elements]

        team_name_parts = soup.find('div', class_='TeamHeader_name__MmHlP').find_all('div')

        # Concatenate the text content of all <div> elements to form the complete team name
        team_name = ' '.join([part.text.strip() for part in team_name_parts])

        coach_name = soup.find('ul', class_='TeamCoaches_list__xqA2i').li.text

        file_path = os.path.join(os.path.dirname(__file__), 'updatedteams', file_name)
        with open(file_path, 'w') as file:
            file.write(team_name + "\n" + team_descriptions.get(url) + "\n" + coach_name + "\n")

        print(f"Working on {team_name}...")

        # Print the extracted player names
        for name in player_names:
            try:
                url = player_links[name]
                scraper = cloudscraper.create_scraper()
                response = scraper.get(url)
                if response.status_code == 200:
                    html_content = response.text
                    soup = BeautifulSoup(html_content, 'html.parser')

                    # Find all <span> elements with class containing "attribute-box"
                    attribute_spans = soup.find_all('span', class_='attribute-box')

                    outside_scoring = int(attribute_spans[0].text.strip())
                    athleticism = int(attribute_spans[7].text.strip())
                    inside_scoring = int(attribute_spans[15].text.strip())
                    playmaking = int(attribute_spans[24].text.strip())
                    defensive_rating = int(attribute_spans[30].text.strip())
                    rebounding = int(attribute_spans[38].text.strip())
                    intangibles = int(attribute_spans[41].text.strip())
                    potential = int(attribute_spans[42].text.strip())
                    
                    # Write data to a text file
                    with open(file_path, 'a', encoding='utf-8') as file:
                        file.write(f"{name},{outside_scoring},{inside_scoring},{athleticism},{playmaking},{defensive_rating},{rebounding},{intangibles},{potential}\n")

                    time.sleep(random.uniform(1, 3))
                else:
                    print(f"Error fetching webpage for player '{name}': {response.status_code}. Skipping...")
            
            except KeyError:
                print(f"Player '{name}' not found in player_links dictionary.")
            except ValueError:
                print(f"Invalid input for player '{name}'. Skipping...")
            except Exception as e:
                print(f"Error processing player '{name}': {e}. Skipping...")

    except Exception as e:
        print("Error fetching webpage:", e)

for url, filename in url_file_mapping.items():
    get_team_data(url, filename)
print("Team data updated successfully.")