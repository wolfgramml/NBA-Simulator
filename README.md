# NBA Simulator

<p>This is a long term project with the intention of creating a simulator to simulate real life NBA teams and players. This may be made into a web app or transition to using JavaFX at some point.</p>

## TODO
<p>Here is a list of features that are yet to be implemented:</p>
<p>Players</p>

- [ ] Add all necessary data to corresponding team's txt file
    - [x] Attribute Ratings
    - [ ] Age
    - [ ] Years Experience
    - [ ] Position
- [ ] Track individual game stats
- [ ] Track average stats


<p>Functionality</p>

- [x] Calculations
    - [x] Match outcome
    - [x] Player stats for matches
- [x] Player stat tracking
- [ ] Team stat tracking

<p>Simulation features</p>

- [ ] Settings
    - [ ] Simulation stop frequency
- [ ] End of season
  - [ ] View team stats/standings
  - [ ] View player stats
  - [ ] Awards
      - [ ] MVP
      - [ ] COTY
      - [ ] DPOY
      - [ ] 1st, 2nd, 3rd Team All NBA

<p>GUI</p>

- [ ] Transition to using JavaFX OR transition to web app

<p>Long term goals</p>

- [ ] Multiple seasons
    - [ ] Generating new players
    - [ ] Player development
- [ ] Save state to view later or to resume simulation
- [ ] More features
  - [ ] Injuries
  - [ ] Trades
  - [ ] Salaries / Salary Cap
- [ ] All star game / all star weekend

## Already Implemented
<p>These features have already been implemented:</p>

- Created txt files for each team and added all necessary info about the teams and players
- Parsed txt files to get info about the team and create Team objects
- Parsed txt files to get info about each player and create Player objects
- Created matchups for each team
- Added all players' names and 2K attribute ratings to associated team's txt file
- Ensured each team has 41 home games and 41 away games
- Created a method to calculate the standings at any given time
- Created script to update teams' rosters
- Calculate player stats for each game
- Store player statline history
- Calculate match outcome based on each player's stats