import {React, useEffect, useState } from 'react';
import { Link , useSearchParams} from 'react-router-dom';
import { MatchDetailComponent } from '../components/MatchDetailComponent';
import { YearSelector } from '../components/YearSelector';

import '../css/MatchPage.scss';

export const MatchPage = () =>{

    const [match,setMatch] = useState({});
    // const { teamName } = useParams();
    const [searchParams, setSearchParams] = useSearchParams();
    const teamName = searchParams.get('teamName');
    const year = searchParams.get('year');
    useEffect(
        () => {
            const fetchTeamData = async () =>{
            const response = await fetch(`http://localhost:8080/dashboard/matchDetails?teamName=${teamName}&year=${year}`)
//            const response = await fetch(`http://localhost:8080/dashboard/matchDetails?teamName=Delhi Capitals&year=2008`)
            const data = await response.json();
            setMatch(data);
            // console.log(match);
           };
        fetchTeamData();
        },[teamName,year]
    );

    if (!match[0]) return (
        <div className='NotFound'>
            <div className='year-selector-not-found'>
                <h2> Select Year </h2>
                <YearSelector teamName={teamName} />
            </div>
            <div className='not-found'>
                <h2>{teamName} played no matches in {year}</h2>
            </div>
        </div>
    );

    return(
        <div className='MatchPage'>
            <div className='year-selector'>
                <h2> Select Year </h2>
                <YearSelector teamName={teamName} />
            </div>
            <div>
            <h1 className="page-heading"><Link to={{pathname: '/dashboard/teamDetails',
                                          search: `?teamName=${teamName}`}}>
                                          {teamName}</Link> matches in {year}</h1>
                {match.map(latest_match => <MatchDetailComponent key={latest_match.id} match={latest_match} teamName = {teamName}/>)}
            </div>
        </div>
    );
}