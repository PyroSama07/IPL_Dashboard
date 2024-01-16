import {React} from 'react';
import { Link } from 'react-router-dom';

import '../css/MatchSmallComponent.scss';

export const MatchSmallComponent = ({match,teamName}) =>{
    if (!match) return null;
    const otherTeam = teamName===match.team1 ? match.team2:match.team1;
    const isMatchWon = teamName===match.winningTeam;
    return (
        <div className = {isMatchWon? 'MatchSmallComponent won-card':'MatchSmallComponent lost-card'}>
            <h2> {teamName} vs <Link to={{pathname: '/dashboard/teamDetails',
                                search: `?teamName=${otherTeam}`}}>
                                {otherTeam}</Link> 
            </h2>
            <p className='match-result'>
                {match.winningTeam} Won By {match.margin} {match.wonBy}
            </p>
        </div>
    );
}