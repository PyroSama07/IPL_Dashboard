import {React, useEffect, useState} from 'react';
import { TeamHome } from '../components/TeamHome';

import '../css/HomePage.scss';

export const HomePage = () =>{

    const [teams,setTeams] = useState({});
    let i = 0;
    useEffect (
        () =>{
            const fetchTeamNames = async () => {
                const response = await fetch(`${process.env.REACT_APP_BACKEND_URL}/dashboard/allTeams`);
                const data = await response.json();
                setTeams(data);
                // console.log(data);
            };
            fetchTeamNames();
        },[]
    );

    if (!teams[0]) return (
        <div>
            not
        </div>
    )

    return (
        <div className="HomePage">
            <div className='title'>
                <h1> IPL DashBoard </h1>
                <h2>by Rochak Ranjan</h2>
            </div>
            {teams.map(team => <TeamHome key={i++} teamName = {team} />)}
        </div>
    );
}