import {React, useEffect, useState } from 'react';
import { useSearchParams, Link} from 'react-router-dom';
import { MatchDetailComponent } from '../components/MatchDetailComponent';
import { MatchSmallComponent } from '../components/MatchSmallComponent';
import { PieChart } from 'react-minimal-pie-chart';

import '../css/TeamPage.scss';

export const TeamPage = () =>{
    
    const [team,setTeam] = useState({latest_matches: []});
    // const { teamName } = useParams();
    const [searchParams, setSearchParams] = useSearchParams();
    const teamName = searchParams.get('teamName');
    useEffect(
        () => {
           const fetchTeamData = async () =>{
            const response = await fetch(`http://localhost:8080/dashboard/teamDetails?teamName=${teamName}`)
            // const response = await fetch(`http://localhost:8080/dashboard/teamDetails?teamName=Delhi Capitals`)
            const data = await response.json();
            setTeam(data);
            // console.log(data);
           };
        fetchTeamData();
        },[teamName]
    );
    
    if (!team.latest_matches) return (
        <div className='NotFound'>
            <h1>Team Not Found</h1>
        </div>
    );

    return(
        <div className='TeamPage'>
            <div className='team-name-section'>
                <h2 className='team-name'>{team.team_name}</h2>
            </div>
            <div className='win-draw-loss-section'>
                Total Matches {team.total_match}
                <PieChart
                data={[
                    { title: 'Losses', value: team.total_loss, color: '#a34d5d' },
                    { title: 'Wins', value: team.total_win, color: '#4da375' },
                    { title: 'Draw', value: team.total_draw, color: '#FFFFFF' }
                ]}
                />
            </div>
            <div className='match-detail-section'>
                <h2>Latest Match</h2>
                <MatchDetailComponent match={team.latest_matches[0]} teamName = {teamName} />
            </div>
            {team.latest_matches.slice(1).map(latest_match => <MatchSmallComponent match={latest_match} teamName = {teamName}/>)}
            <div className='more-link'>
                <p><Link to={{pathname: '/dashboard/matchDetails',
                                                search: `?teamName=${teamName}&year=2022`}}>
                                                more</Link>
                </p>
            </div>
        </div>
    );
}