const jsonServer = require('json-server');
const server = jsonServer.create();
const router = jsonServer.router('db.json');
const middlewares = jsonServer.defaults();

server.use(middlewares);

router.render = (req, res) => {
    // Count total number of records in db.json
    const totalCount = Object.keys(res.locals.data).length;
    res.header('X-Total-Count', totalCount);
    res.jsonp(res.locals.data);
};

server.use(router);
server.listen(8089, () => {
    console.log('JSON Server is running');
});
