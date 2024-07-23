import '../../styles/App.css';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <div className="App">
      <div className="Page">
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
