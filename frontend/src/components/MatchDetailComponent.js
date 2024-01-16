import {React} from 'react';
import { Link } from 'react-router-dom';

import '../css/MatchDetailComponent.scss'

export const MatchDetailComponent = ({match,teamName}) => {
    if (!match) return null;
    const otherTeam = teamName===match.team1 ? match.team2:match.team1;
    // const matchWinner = match.winningTeam;
    const isMatchWon = teamName===match.winningTeam;
    return (
        <div className={isMatchWon ? 'MatchDetailComponent won-card' : 'MatchDetailComponent lost-card'}>
            <div>
                <h2> {teamName} vs <Link to={{pathname: '/dashboard/teamDetails',
                                    search: `?teamName=${otherTeam}`}}>
                                    {otherTeam}</Link> 
                </h2>
                <p className='match-date'>{match.date}</p>
                <p className='match-venue'>{match.venue}</p>
                <p className='match-result'>{match.winningTeam} Won By {match.margin} {match.wonBy}</p>
            </div>
            <div className='additional-detail'>
                <h2>First Innings</h2>
                <p>{match.team1}</p>
                <h2>Second Innings</h2>
                <p>{match.team2}</p>
                <h2>Man of the match</h2>
                <p>{match.playerOfMatch}</p>
                <h2>Umpires</h2>
                <p>{match.umpire1}, {match.umpire2}</p>
            </div>
        </div>
    );
}