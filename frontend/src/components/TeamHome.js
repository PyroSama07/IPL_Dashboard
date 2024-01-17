import {React} from 'react';
import {Link} from 'react-router-dom';

import '../css/TeamHome.scss';

export const TeamHome = ({teamName}) => {
    return (
        <div className='TeamHome'>
            <p><Link to={{pathname: '/dashboard/teamDetails',
                        search: `?teamName=${teamName}`}} >{teamName}</Link>
            </p>
        </div>
    );
}